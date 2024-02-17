package com.bt.MyOrder.common;

public class ArrayTestDemo {

	public static void main(String[] args) {

		int[] arr = { 0,1, 5, 5, 2, 7, 10 };
		System.out.println(arr.length);
		int temp=0;

		
		
		for(int j=0;j<arr.length;j++)
		{
			for (int k=j+1;k<arr.length;k++)
			{
				if(arr[k]<arr[j])
				{
					temp=arr[j];
					arr[j] =arr[k];
					arr[k] = temp;
				}
			}
		}
		
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + ", ");
		}
	}

}
