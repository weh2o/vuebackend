package com.joe.vuebackend.convert;

import com.joe.vuebackend.constant.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.util.Objects;

@Convert
// 實現 AttributeConverter<JAVA類型, 要存入資料庫的類型>
public class GenderConvert implements AttributeConverter<Gender, String> {

    /**
     * 將實體屬性x轉化為y存儲到資料庫中，即插入和更新操作時執行；
     *
     * @param gender 專案裡屬性實際的類別
     * @return 存儲到資料庫裡的資料
     */
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (Objects.nonNull(gender)) {
            return gender.getCode();
        }
        return null;
    }

    /**
     * 將資料庫中的字段y轉化為實體屬性x，即查詢操作時執行。
     *
     * @param dbData 資料庫裡的字段
     * @return 專案裡屬性實際的類別
     */
    @Override
    public Gender convertToEntityAttribute(String dbData) {
        // 取出枚舉類所有物件
        for (Gender gender : Gender.values()) {
            // 判斷哪個資料跟和資料庫裡的一樣，將其返回
            if (gender.getCode().equals(dbData)) {
                return gender;
            }
        }
        return null;
    }
}