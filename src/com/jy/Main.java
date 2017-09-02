package com.jy;

import java.util.ArrayDeque;

public class Main {

	public static void main(String[] args) {
		// 链表1
		ListNode node7 = new ListNode(7, null);
		ListNode node6 = new ListNode(6, node7);
		ListNode node5 = new ListNode(5, node6);
		ListNode node4 = new ListNode(4, node5);

		// 链表2
		ListNode node3 = new ListNode(3, node6);
		ListNode node2 = new ListNode(2, node3);
		ListNode node1 = new ListNode(1, node2);

		System.out.println("==========打印链表==========");

		System.out.print("链表1:");
		ListNode listNode1 = node4;
		while (node4.mNext != null) {
			System.out.print(node4.mValue + "->");
			node4 = node4.mNext;
		}
		System.out.println(node4.mValue);

		System.out.print("链表2:");
		ListNode listNode2 = node1;
		while (node1.mNext != null) {
			System.out.print(node1.mValue + "->");
			node1 = node1.mNext;
		}
		System.out.println(node1.mValue);

		System.out.println("==========第一个公共节点==========");

		System.out.println("链表1和链表2的第一公共节点:" + findFirstCommonNode1(listNode1, listNode2).mValue);
		System.out.println("链表1和链表2的第一公共节点:" + findFirstCommonNode2(listNode1, listNode2).mValue);
	}

	/**
	 * 使用栈结构 查找两个链表的第一个公共节点
	 * 
	 * @param listNode1
	 *            链表1
	 * @param listNode2
	 *            链表2
	 * @return 返回第一个公共节点
	 */
	public static ListNode findFirstCommonNode1(ListNode listNode1, ListNode listNode2) {
		if (listNode1 == null || listNode2 == null)
			throw new RuntimeException("The list should not be null!");

		// 先定义两个栈
		ArrayDeque<ListNode> stack1 = new ArrayDeque<ListNode>();
		ArrayDeque<ListNode> stack2 = new ArrayDeque<ListNode>();

		// 遍历链表填充栈
		while (listNode1 != null) {
			stack1.push(listNode1);
			listNode1 = listNode1.mNext;
		}
		while (listNode2 != null) {
			stack2.push(listNode2);
			listNode2 = listNode2.mNext;
		}
		ListNode temp1 = stack1.pop();
		ListNode temp2 = stack2.pop();
		// 出栈并寻找第一个公共节点
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			if (stack1.peek() != stack2.peek())
				break;
			temp1 = stack1.pop();
			temp2 = stack2.pop();
		}

		if (temp1 == temp2)
			// 返回前先判断一下
			return temp1;
		throw new RuntimeException("there is no common node!");
	}

	/**
	 * 使用先行发 查找两个链表的第一个公共节点
	 * 
	 * @param listNode1
	 *            链表1
	 * @param listNode2
	 *            链表2
	 * @return 返回第一个公共节点
	 */
	public static ListNode findFirstCommonNode2(ListNode listNode1, ListNode listNode2) {
		if (listNode1 == null || listNode2 == null)
			throw new RuntimeException("The list should not be null!");

		int length1 = lengthOfList(listNode1);
		int length2 = lengthOfList(listNode2);

		int lengthDif = length1 - length2;
		ListNode listNodeLong = listNode1;
		ListNode listNodeShort = listNode2;

		// 根据差值来判定那个链表更长
		if (lengthDif < 0) {
			lengthDif = -lengthDif;
			listNodeLong = listNode2;
			listNodeShort = listNode1;
		}
		// 长的链表先向前走多余的几步
		for (int i = 0; i < lengthDif; i++)
			listNodeLong = listNodeLong.mNext;
		while (listNodeLong != null && listNodeShort != null && listNodeLong != listNodeShort) {
			listNodeLong = listNodeLong.mNext;
			listNodeShort = listNodeShort.mNext;
		}

		if (listNodeLong == listNodeShort)
			// 返回前先判断一下
			return listNodeLong;
		throw new RuntimeException("there is no common node!");
	}

	/**
	 * 获取链表的长度
	 * 
	 * @param listNode
	 *            输入的链表
	 * @return 返回链表的长度
	 */
	public static int lengthOfList(ListNode listNode) {
		int length = 0;
		while (listNode != null) {
			length++;
			listNode = listNode.mNext;
		}
		return length;
	}

}
