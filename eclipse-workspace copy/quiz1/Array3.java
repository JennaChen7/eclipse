class Array3
{
	public static void main(String[] args)
	{
		int sum=0;
		try {
			String inn = "10 a 20 b 30";
			java.util.StringTokenizer st= new java.util.StringTokenizer(inn);
			int[] ia=new int[2];
		int x=1;
		int y=2;
		for(int i=0;i<6;i++) {
			try {
				String token = st.nextToken();
				int intvalue=Integer.parseInt(token);
				ia[i]=intvalue;
			}
			catch(NumberFormatException e){
				sum=sum+x;
				System.out.println("NE");
			}
			catch(ArrayIndexOutOfBoundsException e) {
				sum=sum+y;
				System.out.println("AE");
			}
		}
		}
		catch(Exception e) {
			System.out.println("E");
		}
		finally {
			sum +=1;
		}
		System.out.println(sum);
				
		}
	}


