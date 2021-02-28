/**ItemInfoNode class defines a node that will be used 
 * in the linked list class
 * This node contains references to two other nodes (next, prev) and 
 * an ItemInfo object which is the data it carries
 * @version 1, July 20, 2016
 */
public class ItemInfoNode {
	/**This is the reference to the ItemInfo object 
	 * the data that is stored in the list
	 */
	private ItemInfo data;
	/**This is the reference to the previous node in the list
	 */
	private ItemInfoNode prev;
	/**This is the reference to the next node in the list
	 */
	private ItemInfoNode next;
	/**This is the constructor which defines a node
	 * @param data
	 */
	ItemInfoNode(ItemInfo data){
		this.data = data;
		this.next = null;
	}
	/**Mutator method for the data
	 * @param info
	 */
	public void setInfo(ItemInfo info){
		this.data = info;
	}
	/**Accessor method for the data
	 * @return ItemInfo the data referenced by this node
	 */
	public ItemInfo getInfo(){
		return this.data;
	}
	/**Mutator method for the next node
	 * @param node
	 */
	public void setNext(ItemInfoNode node){
		this.next = node;
	}
	/**The accessor method for the next node
	 * @return ItemInfoNode the next node
	 */
	public ItemInfoNode getNext(){
		return next;
	}
	/**The mutator method for the previous node
	 * @param node
	 */
	public void setPrev(ItemInfoNode node){
		this.prev = node;
	}
	/**The accessor method for the previous node
	 * @return ItemInfoNode the previous node
	 */
	public ItemInfoNode getPrev(){
		return prev;
	}
}
