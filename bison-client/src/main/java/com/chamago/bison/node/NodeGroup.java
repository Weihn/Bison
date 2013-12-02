package com.chamago.bison.node;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gavin.peng
 * 
 * 2013-10-16 下午04:20:58
 × bison-client
 */
public class NodeGroup
{
  private String groupID;
  private String groupName;
  private List<MinaNode> lst = new ArrayList();

  private int pos = 0;

  public String getGroupID() {
    return this.groupID;
  }
  public void setGroupID(String groupID) {
    this.groupID = groupID;
  }
  public String getGroupName() {
    return this.groupName;
  }
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public void addNode(MinaNode objNode) {
    this.lst.add(objNode);
  }

  public synchronized MinaNode getNode() {
    MinaNode obj = null;
    for (int i = this.pos; i < this.lst.size(); i++) {
      if (((MinaNode)this.lst.get(i)).isConnected()) {
        obj = (MinaNode)this.lst.get(i);
        this.pos += 1;
        if (this.pos < this.lst.size()) break;
        this.pos = 0;

        break;
      }
    }
    if (obj == null) {
      for (int i = 0; i < this.pos; i++) {
        if (((MinaNode)this.lst.get(i)).isConnected()) {
          obj = (MinaNode)this.lst.get(i);
          this.pos = i;
          break;
        }
      }
    }
    return obj;
  }
}