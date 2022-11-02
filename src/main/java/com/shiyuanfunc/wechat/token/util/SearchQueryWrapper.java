package com.shiyuanfunc.wechat.token.util;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import com.shiyuanfunc.wechat.token.ProductDocDTO;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author MUSI
 * @Date 2022/10/28 10:15 PM
 * @Description
 * @Version
 **/
public class SearchQueryWrapper {


    private List<SearchCondition> conditions = new ArrayList<>();


    public <T> SearchQueryWrapper eq(SearchMethodFunc<T, Object> beanFunc, Object value){
        String fieldName = beanFunc.getFieldName();
        conditions.add(new SearchCondition(Operator.TERMS, fieldName, value));
        return this;
    }

    public Query builder(List<SearchCondition> conditions){
        Query query = new Query.Builder().build();
        if (conditions.isEmpty()) {
            return query;
        }
        BoolQuery bool = query.bool();
        for (SearchCondition condition : conditions) {
//            bool.must().add()
        }
        return query;
    }

    private Query buildQuery(SearchCondition condition){
        switch (condition.getOperator()){
            case TERMS:

                break;

        }
        return null;
    }

    public static void main(String[] args) {
        SearchQueryWrapper searchQueryWrapper = new SearchQueryWrapper();
        searchQueryWrapper.eq(ProductDocDTO::getApplyType, 10);

        System.out.println(Introspector.decapitalize("IsShow"));
    }
}
