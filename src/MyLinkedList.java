
    //for convenience to create a linkedlist

	public class MyLinkedList{
		private Node firstNode;
		
		//MyLinkedList canbe constructed as MyLinkedList(array);
		public MyLinkedList(Object[] values){
			firstNode = new Node(values[0]);
			Node tempNode = firstNode;
			for(int i=1;i<values.length;i++){
				
				if(tempNode.next == null){
					tempNode.next = new Node(values[i]);
					tempNode = tempNode.next;
				}
				
			}
		}
		
		//Construct the toString() method to help print MyLinkedList
		public String toString(){
			Node current = firstNode;
			StringBuffer output = new StringBuffer("");
			for(current=firstNode;current!=null;current=current.next){
				output.append(current.getValue()+"-->");
			}
			output.append("End");
			return output.toString();
		}
		
		public void reverseList(){
			
			recursiveReverse(firstNode);
			
			
			
		}
		
		public Node getFirst(){
			return firstNode;
		}

		private Node recursiveReverse(Node currentNode) {
			// TODO Auto-generated method stub
			if(currentNode.next == null){ //find the original tail
				firstNode = currentNode;  //make it the head
				return firstNode;
			} else{
				Node previousNode = recursiveReverse(currentNode.next);
				//recursively reverse the list:
				//e.g: 1->2->3->4: (2->3->4)->1
				
				while(previousNode.next!=null){
					previousNode = previousNode.next;					
				}//find the tail of the sublist and point to the current one
				previousNode.next = currentNode;
				currentNode.next = null;//update the current one to be null
			}
			return firstNode;
		}
		
			
			
	}
	
	
	
