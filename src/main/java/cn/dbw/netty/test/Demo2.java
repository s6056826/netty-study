package cn.dbw.netty.test;

import net.sf.json.JSONArray;

public class Demo2 {
	/**
	 * [{"CityId":18,"CityName":"����","ProvinceId":27,"CityOrder":1},{"CityId":53,"CityName":"����","ProvinceId":27,"CityOrder":1}] 
	 * ����һ��json��ʽ���ַ�������ͨ��java������CityOrder��ֵ��
	 */
	public static void main(String[] args) {
		String str="[{\"CityId\":18,\"CityName\":\"����\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"����\",\"ProvinceId\":27,\"CityOrder\":2}]";
		JSONArray array = JSONArray.fromObject(str);
		for(int i=0;i<array.size();i++){
			 System.out.println(array.getJSONObject(i).getString("CityOrder"));
		}
	}

}
