package com.ipx.common.validator.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 验证单元,这里主要考虑到要排序
 *
 * @param <T>
 */
public class ValidateUnit<T> implements Comparable<ValidateUnit<T>> {

    private Member member;
    private int order;
    private T target;
    private List<Annotation> annos;

    public ValidateUnit(Member member, int order, List<Annotation> annos, T target) {
        this.member = member;
        this.order = order;
        this.annos = annos;
        this.target = target;
    }

    public ValidateUnit(Method member, int order, List<Annotation> annos, T target) {
        this.member = member;
        this.order = order;
        this.annos = annos;
        this.target = target;
    }

    public ValidateUnit(Field member, int order, List<Annotation> annos, T target) {
        this.member = member;
        this.order = order;
        this.annos = annos;
        this.target = target;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public List<Annotation> getAnnos() {
        return annos;
    }

    public void setAnnos(List<Annotation> annos) {
        this.annos = annos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidateUnit<?> that = (ValidateUnit<?>) o;

        if (order != that.order) return false;
        if (!member.equals(that.member)) return false;
        if (!target.equals(that.target)) return false;
        return annos.equals(that.annos);
    }

    @Override
    public int hashCode() {
        int result = member.hashCode();
        result = 31 * result + order;
        result = 31 * result + target.hashCode();
        result = 31 * result + annos.hashCode();
        return result;
    }

    @Override
    public int compareTo(ValidateUnit<T> o) {

        return this.order - o.order;
    }

    @Override
    public String toString() {
        return "ValidateUnit [member=" + member + ", order=" + order + ", target=" + target + ", annos=" + annos + "]";
    }


}
