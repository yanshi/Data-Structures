
public class Stack {
	
	Node top;
	
	public Object pop(){
		if(top!=null){
			Object item = top.getValue();
			top = top.next;
			return item;
		} else {
			return null;
		}		
	}
	
	public void push(Object item){
		Node in = new Node(item);
		in.next = top;
		top = in;
	}

}
