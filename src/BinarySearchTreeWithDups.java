import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable
{
	public BinarySearchTreeWithDups()
	{
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry)
	{
		super(rootEntry);
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	@Override
	public T add(T newEntry)
	{
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
			result = addEntryHelperIterative(newEntry);
		return result;
	}

	// ??? IMPLEMENT THIS METHOD
	private T addEntryHelperIterative(T newEntry)
	{
		// IMPORTANT: METHOD MUST BE ITERATIVE!!
		BinaryNodeInterface<T> currentNode = getRootNode();
	    BinaryNodeInterface<T> newNode = new BinaryNode(newEntry);
	    assert (currentNode != null);
	    T result = null;
	    boolean isFound = false;

	    while (!isFound)
	    {
	        T currentEntry = currentNode.getData();
    	        int compared = newEntry.compareTo(currentEntry);
        
        	    if (compared == 0)
            {
        	        if (currentNode.hasRightChild())
        	        {
        	              currentNode = currentNode.getRightChild();
        	        }
        	        else
    	            {
        	              currentNode.setRightChild(newNode);
        	              isFound = true;
        	              result = currentNode.getData();
    	            }
    
    	         }
    	        else if (compared < 0)
    	        {
    	            if (currentNode.hasLeftChild())
                {
    	                  currentNode = currentNode.getLeftChild();
    	            }
    	            else
    	            {
    	                  isFound = true;
    	                  currentNode.setLeftChild(new BinaryNode<T>(newEntry));
    	            }
    	        }
    	        else
    	        {
    	            assert compared > 0;
    
    	            if (currentNode.hasRightChild())
    	            {
    	                  currentNode = currentNode.getRightChild();
    	            }
    	            else
    	            {
    	                  isFound = true;
    	                  currentNode.setRightChild(new BinaryNode<T>(newEntry));
    	            }
    	        }
    	    }
    	    return result;
	}
	
	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesIterative(T searchVal)
	{
        StackInterface<BinaryNodeInterface<T>> nodeStack = new LinkedStack<>();
        BinaryNodeInterface<T> currentNode = getRootNode();
        ArrayList<T> entryList = new ArrayList<T>();
        while (!nodeStack.isEmpty() || (currentNode != null))
        {
             while (currentNode != null)
             {
                   nodeStack.push(currentNode);
                   currentNode = currentNode.getLeftChild();
             }
             if (!nodeStack.isEmpty())
             {
                   BinaryNodeInterface<T> nextNode = nodeStack.pop();
                   assert nextNode != null;
                    T currentEntry = nextNode.getData();
                    int comparison = searchVal.compareTo(currentEntry);
                    if (comparison == 0)
                    {
                          entryList.add(nextNode.getData());
                    }
                    currentNode = nextNode.getRightChild();
             }
        }
        return entryList;
	}

	// ??? IMPLEMENT THIS METHOD
    public ArrayList<T> getAllEntriesRecursive(T searchVal)
    {
        BinaryNodeInterface<T> rootNode = getRootNode();
        ArrayList<T> entryList = new ArrayList<T>();
        entryList = getAllEntriesHelper(searchVal, rootNode, entryList);
        return entryList;
    }

    private ArrayList<T> getAllEntriesHelper(T searchVal,BinaryNodeInterface<T> rootNode, ArrayList<T> entryList)
    {
        ArrayList<T>result = new ArrayList<>();
        T val = rootNode.getData();
        if (searchVal.equals(val))
            result.add(rootNode.getData());
        if(rootNode.hasLeftChild())
            result.addAll(getAllEntriesHelper(searchVal,rootNode.getLeftChild(),entryList));
        if(rootNode.hasRightChild())
            result.addAll(getAllEntriesHelper(searchVal,rootNode.getRightChild(),entryList));
        return result;
    }
    
    // ??? IMPLEMENT THIS METHOD
    public ArrayList<T> getAllEntriesLessThanIterative(T searchVal)
    {
        ArrayList<T> entryList = new ArrayList<T>();
        T val = getRootNode().getData();
        Stack<BinaryNodeInterface<T>> nodeStack = new Stack<BinaryNodeInterface<T>>();
        nodeStack.push(getRootNode());
        while(!nodeStack.isEmpty()) 
        {
           if (searchVal.compareTo(val) > 0) 
              nodeStack.push(getRootNode().getLeftChild());
           else if (searchVal.compareTo(val) < 0)
              nodeStack.push(getRootNode().getRightChild());
           else
              entryList = (ArrayList<T>) nodeStack.pop();          
        }
        return entryList;
     }
    
    // ??? IMPLEMENT THIS METHOD
     public ArrayList<T> getAllEntriesLessThanRecursive(T searchVal)
     {
        BinaryNodeInterface<T> rootNode = getRootNode();
        ArrayList<T> entryList = new ArrayList<T>();
        entryList = getAllEntriesLessThanHelper(searchVal, rootNode, entryList);
        Collections.sort(entryList) ;
        return entryList;
      }
    
      private ArrayList<T> getAllEntriesLessThanHelper(T searchVal, BinaryNodeInterface<T> rootNode,
                                                       ArrayList<T> entryList)
      {
        ArrayList<T>result = new ArrayList<>();
        T val = rootNode.getData();
        if (searchVal.compareTo(val) > 0)
          result.add(val);
        if(rootNode.hasLeftChild())
          result.addAll(getAllEntriesLessThanHelper(searchVal,rootNode.getLeftChild(),entryList));
        if(rootNode.hasRightChild())
          result.addAll(getAllEntriesLessThanHelper(searchVal,rootNode.getRightChild(),entryList));
        return result;
      }
}