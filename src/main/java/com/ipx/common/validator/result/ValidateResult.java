package com.ipx.common.validator.result;

import com.ipx.common.validator.util.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 验证结果
 */
public class ValidateResult implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -6570391431408851416L;

    /**
     * 错误列表
     */
    private Map<Integer,ValidateResult> errorListMap;
    /**
     * 验证结果
     */
    private Map<String,Object> errorMap;

    /**
     * 组合验证结果
     */
    private List<String> combineErrorList;

    private boolean isList;

    public ValidateResult()
    {
        this.errorMap = new HashMap<>();
        this.combineErrorList = new ArrayList<>();
        this.isList = false;
    }

    public ValidateResult(boolean isList)
    {

        this.isList = isList;
        if(this.isList)
        {
            this.errorListMap = new HashMap<>();
        }
    }


    public void addErrors(Map<String, ? extends  Object> map)
    {
        if(map == null||map.isEmpty())
            return;
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            if(entry.getKey().equals(Constant.DEFAULT_KEY))
            {
                this.combineErrorList.add((String)entry.getValue());
            }else{
                this.errorMap.put(entry.getKey(),entry.getValue());
            }
        }
    }

    public void addErrors(int index,ValidateResult vr)
    {
        this.errorListMap.put(index,vr);
    }

    public void addError(String field,String message)
    {
        this.errorMap.put(field, message);
    }


    /**
     * 是否有错
     * @return
     */
    public boolean hasErrors()
    {
        return (this.isList && (!this.errorListMap.isEmpty()|| !this.combineErrorList.isEmpty()) )||(!this.isList && !this.errorMap.isEmpty());
    }

    /**
     * 错误消息
     * @return
     */
    public Map getMapMessage()
    {
        return this.isList?this.errorListMap:this.errorMap;
    }

    /**
     * 组合验证错误
     * @return
     */
    public List<String> getCombineErrors()
    {
        return this.combineErrorList;
    }

    @Override
    public String toString() {
        return "ValidateResult{" +
                "errorListMap=" + errorListMap +
                ", errorMap=" + errorMap +
                ", combineErrorList=" + combineErrorList +
                ", isList=" + isList +
                '}';
    }
}
