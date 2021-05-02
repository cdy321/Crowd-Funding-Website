package cn.cdy.crowd.controller;


import cn.cdy.crowd.api.MySqlRemoteService;
import cn.cdy.crowd.config.OSSProperties;
import cn.cdy.crowd.entity.vo.*;
import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.CrowdUtil;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectConsumerController {
    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private MySqlRemoteService mySqlRemoteService;
    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(TProjectVO projectVO,
                                       MultipartFile headerPicture,
                                       List<MultipartFile> detailPictureList,
                                       HttpSession session,
                                       ModelMap map) throws IOException {
        boolean flag = headerPicture.isEmpty();
        if(flag){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.HEADER_PIC_EMPTY);
            return "project-launch";
        }
            ResultEntity<String> uploadHeaderFileToOSSResultEntity = CrowdUtil.uploadFileToOSS(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(), 
                    ossProperties.getAccessKeySecret(),
                    headerPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    headerPicture.getOriginalFilename());
            String result = uploadHeaderFileToOSSResultEntity.getResult();
            if(ResultEntity.SUCCESS.equals(result)){
                String headerPicturePath = uploadHeaderFileToOSSResultEntity.getData();
                projectVO.setHeaderPicturePath(headerPicturePath);
            }else{
                map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.DETAIL_PIC_EMPTY);
                return "project-launch";
            }

        List<String> detailPicturePathList = new ArrayList<>();
            if(detailPictureList == null|| detailPictureList.size() == 0){
                map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.DETAIL_PIC_EMPTY);
                return "project-launch";
            }
        for (MultipartFile detailPicture : detailPictureList) {
            if(detailPicture.isEmpty()){
                map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.HEADER_PIC_UPLOAD_FAIL);
                return "project-launch";
            }

            ResultEntity<String> uploadDetailFileToOSS = CrowdUtil.uploadFileToOSS(
                        ossProperties.getEndPoint(),
                        ossProperties.getAccessKeyId(),
                        ossProperties.getAccessKeySecret(),
                        detailPicture.getInputStream(),
                        ossProperties.getBucketName(),
                        ossProperties.getBucketDomain(),
                        detailPicture.getOriginalFilename());
                String result1 = uploadDetailFileToOSS.getResult();
                if(ResultEntity.SUCCESS.equals(result1)){
                    String detailPicturePath = uploadDetailFileToOSS.getData();
                    detailPicturePathList.add(detailPicturePath);
                }else{
                    map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.DETAIL_PIC_UPLOAD_FAIL);
                }

        }
        projectVO.setDetailPicturePathList(detailPicturePathList);
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT,projectVO);
        return "redirect:http://21t041718c.imwork.net/project/repay/setting/page.html";
    }
    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> uploadReturnPicture(
            @RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {
        ResultEntity<String> uploadReturnFileToOSS = CrowdUtil.uploadFileToOSS(ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename());
        return uploadReturnFileToOSS;
    }

    @ResponseBody
    @RequestMapping("/create/save/return.json")
    public ResultEntity<String> saveReturn(TReturnVO returnVO, HttpSession session){
        try{
            TProjectVO projectVO = (TProjectVO)session.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);
            if(projectVO == null){
                return ResultEntity.fail(CrowdConstant.MESSAGE_TEMP_PROJECT_MISSING);
            }
            List<TReturnVO> returnVOList = projectVO.getReturnVOList();
            if(returnVOList == null||returnVOList.size() == 0){
                returnVOList = new ArrayList<>();
                projectVO.setReturnVOList(returnVOList);
            }
            returnVOList.add(returnVO);
            session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT,projectVO);
            return ResultEntity.successWithoutData();

        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }
    @RequestMapping("/create/confirm")
    public String saveConfirm(HttpSession session,
                              TMemberConfirmInfoVO memberConfirmInfoVO,
                              ModelMap map){
         TProjectVO projectVO = (TProjectVO)session.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);
        System.out.println(projectVO);
         if(projectVO == null){
             throw new RuntimeException(CrowdConstant.MESSAGE_TEMP_PROJECT_MISSING);
         }
         projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);
        TMemberLoginVO memberLoginVO = (TMemberLoginVO)session.getAttribute(CrowdConstant.ATTR_LOGIN_MEMBER);
        Integer memberId = memberLoginVO.getId();
        ResultEntity<String> saveResultEntity = mySqlRemoteService.saveProjectRemote(projectVO, memberId);
        String result = saveResultEntity.getResult();
        if(ResultEntity.FAILED.equals(result)){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,saveResultEntity.getMessage());
            return "project-confirm";
        }
        session.removeAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);
        return "redirect:http://21t041718c.imwork.net/project/create/success";
    }

    @RequestMapping("/get/project/detail/{projectId}")
    public String getProjectDetail(@PathVariable("projectId") Integer projectId, Model model) {
        ResultEntity<DetailProjectVO> resultEntity = mySqlRemoteService.getDetailProjectVORemote(projectId);
        if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            DetailProjectVO detailProjectVO = resultEntity.getData();
            model.addAttribute("detailProjectVO", detailProjectVO);
        }
        return "project-show-detail";
    }
}
