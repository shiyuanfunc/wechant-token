package com.shiyuanfunc.wechat.token.compreFace.facade;

import com.shiyuanfunc.common.api.BaseResponse;
import com.shiyuanfunc.wechat.token.compreFace.domain.subject.adapter.SubjectAdapter;
import com.shiyuanfunc.wechat.token.compreFace.facade.vo.SubjectInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author MUSI
 * @Date 2023/1/7 6:53 PM
 * @Description
 * @Version
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/subject")
public class SubjectController {

    private final SubjectAdapter subjectAdapter;

    @PostMapping(path = "/add")
    public BaseResponse<?> addSubject(@RequestBody SubjectInfo subjectInfo){
        subjectAdapter.addSubject(subjectInfo.getSubjectName());
        return BaseResponse.success();
    }

    @PostMapping(path = "/rename")
    public BaseResponse<?> renameSubject(@RequestBody SubjectInfo subjectInfo){
        subjectAdapter.renameSubject(subjectInfo.getOldSubjectName(), subjectInfo.getSubjectName());
        return BaseResponse.success();
    }

    @RequestMapping(path = "/delete")
    public BaseResponse<?> deleteSubject(@RequestBody SubjectInfo subjectInfo){
        subjectAdapter.deleteSubject(subjectInfo.getOldSubjectName());
        return BaseResponse.success();
    }

}
