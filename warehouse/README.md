# Warehouse

This is a warehouse.

## What:
Lot's of objects that make up a warehouse. We started with box, the boxes are on shelves, and shelves are in a warehouse. We also have urgent box, which is a subtype of box. 

## What's happening?

All the fun stuff is in `warehouse.java`. We can add and remove boxes. We can add boxes to shipping lists.
The things to know are: boxes and shelves have their respective heights and lengths

Now imagine this scenario:
Each day you receive and ship boxes. But the placement of the boxes may become non optimal. Let us see why on an example : there are two shelves with height 15 and 20 respectively and length 10 for both. You receive a ﬁrst box of height 15 and length 10. You place it on the ﬁrst shelf (of height 15). You now receive a second box of height 15 and length 10, so you place it on the second shelf. And then you ship the ﬁrst box. So the ﬁrst shelf is empty and the second shelf has the box of height 15. Now if you receive a third box of height 16, you have to send it back saying “sorry, no room left”. But if you had moved the second box on the ﬁrst shelf, you could have stored the third box on the last shelf !

We can use the merge sort algorithm to optimize the warehouse: ie once a box is taken off a shelf 
make the other boxes fit better. This is done in the reorganize() method. 

We also can only optimize with oneBox, and not all the of them.


