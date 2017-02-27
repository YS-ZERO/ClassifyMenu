package net.yszero.classifymenu;

/**
 * Created by YS-WENJIE on 2016/9/13.
 */
public class ItemListVO {

    private String itemName;    //条目名称
    private String[] sonItems;  //子条目名称集合


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String[] getSonItems() {
        return sonItems;
    }

    public void setSonItems(String[] sonItems) {
        this.sonItems = sonItems;
    }
}
