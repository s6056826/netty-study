package cn.dbw.netty.test;
/**
 * �뽫15��34��5��8��19��21 ����ĳ�������㷨��������(�Ӵ�С������������ð�ݣ�ѡ���)������ע�����Ӷȣ�
 */
public class Test3 {
	
	public static void main(String[] args) {
		BubbleSort();
		selectSort();
	}
	
	
	public static void BubbleSort(){
		int nums[] ={15,34,5,8,19,21};
		System.out.println("------ð������------");
		for(int i=0;i<nums.length;i++){
			for(int j=0;j<nums.length-1-i;j++){
				if(nums[j]<nums[j+1]){
					int temp=nums[j];
					nums[j]=nums[j+1];
					nums[j+1]=temp;
				}
			}
		}
		for(int num:nums){
			System.out.println(num);
		}
		System.out.println("�㷨���Ӷ�O(n^2)");
		System.out.println("------ð������End------");
	}
	
	public static void selectSort(){
		System.out.println("------ѡ������------");
		int nums[] ={15,34,5,8,19,21};
        for(int i = 0; i < nums.length - 1; i++) {// ����i������
            int k = i;
            for(int j = k + 1; j < nums.length; j++){// ѡ��С�ļ�¼
                if(nums[j] > nums[k]){ 
                    k = j; //����Ŀǰ�ҵ�����Сֵ���ڵ�λ��
                }
            }
            //���ڲ�ѭ��������Ҳ�����ҵ�����ѭ������С�����Ժ��ٽ��н���
            if(i != k){  //����a[i]��a[k]
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }    
        }
        for(int num:nums){
			System.out.println(num);
		}
        System.out.println("ѡ�������Ӷ�O(n^2)");
		System.out.println("------ѡ������End------");
	}
	
	
	
	

}
