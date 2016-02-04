class NameTable<Base>
{
	private class TreeNode
	{
		private String key;
		private Base value;
		private TreeNode left = null;
		private TreeNode right = null;
		private TreeNode(String key, Base value)
		{
			this.key = key;
			this.value = value;
		}
	}
	private class StackNode
	{
		private TreeNode root;
		private StackNode next;
		private StackNode(TreeNode root, StackNode next)
		{
			this.root = root;
			this.next = next;
		}
	}
	private StackNode top;
	private TreeNode head;
	public NameTable()
	{
		head = new TreeNode("", null);
		top = null;
	}
	StackNode stackNode;
	Base tempValue;
	public Base get(String key)
	{
		stackNode = top;
		while(stackNode != null)
		{
			tempValue = helperGet(key, stackNode);
			if(tempValue != null)
			{
				return tempValue;
			}
			stackNode = stackNode.next;
		}
		throw new IllegalArgumentException("No such key in all stacks.");
	}
	public boolean has(String key)
	{
		stackNode = top;
		while(stackNode != null)
		{
			tempValue = helperGet(key, stackNode);
			if(tempValue != null)
			{
				return true;
			}
			stackNode = stackNode.next;
		}
		return false;
	}
	private Base helperGet(String key, StackNode stackNode)
	{
		TreeNode subtree = stackNode.root;
		while(subtree != null)
		{
			int test = key.compareTo(subtree.key);
			if(test < 0)
			{
				subtree = subtree.left;
			}
			else if(test > 0)
			{
				subtree = subtree.right;
			}
			else
			{
				return subtree.value;
			}
		}
		return null;
	}
	public boolean isEmpty()
	{
		return top == null;
	}
	public void pop()
	{
		if(isEmpty())
		{
			throw new IllegalStateException("Stack is empty.");
		}
		else
		{
			top = top.next;
		}
	}
	public void push()
	{
		top = new StackNode(null, top);
	}
	public void put(String key, Base value)
	{
		head.right = top.root;
		top.root = head;
		TreeNode temp = head;
		while(true)
		{
			int test = key.compareTo(temp.key);
			if(test < 0)
			{
				if(temp.left == null)
				{
					temp.left = new TreeNode(key, value);
					break;
				}
				else
				{
					temp = temp.left;
				}
			}
			else if(test > 0)
			{
				if(temp.right == null)
				{
					temp.right = new TreeNode(key, value);
					break;
				}
				else
				{
					temp = temp.right;
				}
			}
			else
			{
				throw new IllegalArgumentException("Key existed.");
			}
		}
		top.root = head.right;
	}
}
class ScopesTrial  
{  
  public static void main(String [] args)  
  {  
    NameTable<Integer> table = new NameTable<Integer>();  
    System.out.println(table.isEmpty());  //  Print true.  
  	System.out.println();

    table.push();                         //  Enter a scope.  
    System.out.println(table.isEmpty());  //  Print false.  
    System.out.println();
  
    table.put("a", 1);                    //  Add variable "a".  
    System.out.println(table.has("a"));   //  Print true.  
    System.out.println();
  
    table.push();                         //  Enter a scope.  
    table.put("t", 2);                    //  Add variable "t".  
    System.out.println(table.has("a"));   //  Print true.  
    System.out.println(table.has("t"));   //  Print true.  
    System.out.println();
  
    table.push();                         //  Enter a scope.  
    table.put("k", 3);                    //  Add variable "k".  
    System.out.println(table.has("a"));   //  Print true.  
    System.out.println(table.has("t"));   //  Print true.  
    System.out.println(table.has("k"));   //  Print true.  
    System.out.println();
  
    table.push();                         //  Enter a scope.  
    table.put("e", 4);                    //  Add variable "e".  
    System.out.println(table.has("a"));   //  Print true.  
    System.out.println(table.has("t"));   //  Print true.  
    System.out.println(table.has("k"));   //  Print true.  
    System.out.println(table.has("e"));   //  Print true.  
    System.out.println();
  
    table.pop();                          //  Exit a scope.  
    System.out.println(table.has("a"));   //  Print true.  
    System.out.println(table.has("t"));   //  Print true.  
    System.out.println(table.has("k"));   //  Print true.  
    System.out.println(table.has("e"));   //  Print false.  
    System.out.println();
  
    table.pop();                          //  Exit a scope.  
    System.out.println(table.has("a"));   //  Print true.  
    System.out.println(table.has("t"));   //  Print true.  
    System.out.println(table.has("k"));   //  Print false.  
    System.out.println(table.has("e"));   //  Print false.  
    System.out.println();
  
    table.pop();                          //  Exit a scope.  
    System.out.println(table.has("a"));   //  Print true.  
    System.out.println(table.has("t"));   //  Print false.  
    System.out.println(table.has("k"));   //  Print false.  
    System.out.println(table.has("e"));   //  Print false.  
    System.out.println();
  
    table.pop();                          //  Exit a scope.  
    System.out.println(table.has("a"));   //  Print false.  
    System.out.println(table.has("t"));   //  Print false.  
    System.out.println(table.has("k"));   //  Print false.  
    System.out.println(table.has("e"));   //  Print false.  
    System.out.println();
  
    System.out.println(table.isEmpty());  //  Print true.  
  }  
}