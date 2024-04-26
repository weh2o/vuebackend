package com.joe.vuebackend.bean;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 刪除結果
 */
@Data
public class DeleteResult<T> {

    /**
     * 刪除失敗的資料
     */
    List<T> failList;

    /**
     * 刪除成功的資料
     */
    List<T> successList;

    public void addFail(T info) {
        if (CollectionUtils.isEmpty(failList)) {
            failList = new ArrayList<>();
        }
        if (Objects.nonNull(info)) {
            failList.add(info);
        }
    }

    public void addSuccess(T info) {
        if (CollectionUtils.isEmpty(successList)) {
            successList = new ArrayList<>();
        }
        if (Objects.nonNull(info)) {
            successList.add(info);
        }
    }
}
