package cn.dbw.netty.test;
/**
 * 请将15，34，5，8，19，21 按照某种排序算法进行排序(从大到小进行排序，例如冒泡，选择等)，并标注出复杂度；
 */
public class Test3 {
	
	public static void main(String[] args) {
		BubbleSort();
		selectSort();
	}
	
	
	public static void BubbleSort(){
		int nums[] ={15,34,5,8,19,21};
		System.out.println("------冒泡排序------");
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
		System.out.println("算法复杂度O(n^2)");
		System.out.println("------冒泡排序End------");
	}
	
	public static void selectSort(){
		System.out.println("------选择排序------");
		int nums[] ={15,34,5,8,19,21};
        for(int i = 0; i < nums.length - 1; i++) {// 做第i趟排序
            int k = i;
            for(int j = k + 1; j < nums.length; j++){// 选最小的记录
                if(nums[j] > nums[k]){ 
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if(i != k){  //交换a[i]和a[k]
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }    
        }
        for(int num:nums){
			System.out.println(num);
		}
        System.out.println("选择排序复杂度O(n^2)");
		System.out.println("------选择排序End------");
	}
	
	
	
	

}
