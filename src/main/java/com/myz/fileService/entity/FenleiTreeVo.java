package com.myz.fileService.entity;

import lombok.Data;

import java.util.List;

@Data
public class FenleiTreeVo {
    private  Integer value;
    private String label;
    private List<FenleiTreeVo> children;
}
