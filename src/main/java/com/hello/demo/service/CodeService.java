package com.hello.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.demo.entity.CodeDetail;
import com.hello.demo.entity.CodeGroup;
import com.hello.demo.repository.CodeDetailRepository;
import com.hello.demo.repository.CodeGroupRepository;

@Service
public class CodeService {

    @Autowired
    private CodeGroupRepository codeGroupRepository;

    @Autowired
    private CodeDetailRepository codeDetailRepository;

    public List<CodeGroup> getAllCodeGroups() {
        return codeGroupRepository.findAll();
    }

    public CodeGroup saveCodeGroup(CodeGroup codeGroup) {
        return codeGroupRepository.save(codeGroup);
    }

    public void deleteCodeGroup(String groupId) {
        codeGroupRepository.deleteById(groupId);
    }

    public List<CodeDetail> getCodeDetails(String groupId) {
        return codeDetailRepository.findByGroupId(groupId);
    }

    public CodeDetail saveCodeDetail(CodeDetail codeDetail) {
        return codeDetailRepository.save(codeDetail);
    }

    public void deleteCodeDetail(String codeId) {
        codeDetailRepository.deleteById(codeId);
    }
}
