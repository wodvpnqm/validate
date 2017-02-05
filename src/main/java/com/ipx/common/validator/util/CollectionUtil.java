package com.ipx.common.validator.util;

import java.util.List;
import java.util.Map;

public class CollectionUtil {

	private CollectionUtil(){}
	
	
	public static <K,V>  Map<K,V> mergeMap(Map<K,V> map1,Map<K,V> map2)
	{
		if(map1 == null)
			return map2;
		if(map2 == null)
			return map1;
		map1.putAll(map2);
		return map1;
	}
	
	
	public static <T> List<T> mergeList(List<T> lst1,List<T> lst2)	
	{
		if(lst1 == null)
			return lst2;
		if(lst2 ==  null)
			return lst1;
		lst1.addAll(lst2);
		return lst1;
	}
	
}
