/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.api.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import org.spout.api.inventory.util.InventoryIterator;
import org.spout.api.material.BlockMaterial;
import org.spout.api.material.Material;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class InventoryTest {
	private final Material[] mats = {BlockMaterial.AIR, BlockMaterial.SOLID, BlockMaterial.UNBREAKABLE};
	private final Random random = new Random();
	private List<ItemStack> items = new ArrayList<ItemStack>(3);
	private Inventory inventory = new Inventory(20);

	@Before
	public void constructRandomInventory() {
		for (int i = 0; i < inventory.size(); i++) {
			inventory.set(i, getRandomItem());
		}
	}

	@Before
	public void constructItemList() {
		items.add(new ItemStack(mats[0], getRandomSize()));
		items.add(new ItemStack(mats[1], getRandomSize()));
		items.add(new ItemStack(mats[2], getRandomSize()));
	}

	@Test
	public void testSize() {
		assertEquals(20, inventory.size());
	}

	@Test
	public void testContains() {
		ItemStack item = getRandomItem();
		int slot = getRandomSlot();
		inventory.set(slot, item);
		assertTrue(inventory.contains(item));
	}

	@Test
	public void testIteratorForwards() {
		InventoryIterator i = inventory.listIterator();
		int index = 0;
		while (i.hasNext()) {
			assertEquals(inventory.get(index++), i.next());
		}
	}

	@Test
	public void testIteratorBackwards() {
		int index = inventory.size() - 1;
		InventoryIterator i = inventory.listIterator(index);
		while (i.hasPrevious()) {
			assertEquals(inventory.get(index--), i.previous());
		}
	}

	@Test
	public void testAdd() {
		ItemStack item = getRandomItem();
		ItemStack item1 = getRandomItem();
		inventory.set(0, item);
		inventory.set(1, null);
		inventory.add(item1);
		assertEquals(item1, inventory.get(1));
	}

	@Test
	public void testRemove() {
		ItemStack item = getRandomItem();
		int slot = getRandomSlot();
		inventory.set(slot, item);
		inventory.remove(item);
		assertNull(inventory.get(slot));
	}

	@Test
	public void testAddAll() {
		inventory.addAll(items);
		assertTrue(inventory.containsAll(items));
	}

	@Test
	public void testRemoveAll() {
		inventory.removeAll(items);
		for (ItemStack item : items) {
			assertFalse(inventory.contains(item));
		}
	}

	@Test
	public void testRetainAll() {
		ItemStack item = getRandomItem();
		inventory.add(item);
		inventory.addAll(items);
		inventory.retainAll(items);
		assertFalse(inventory.contains(item));
	}

	@Test
	public void testSetData() {
		int slot = getRandomSlot();
		int data = getRandomSize();
		inventory.setData(slot, data);
		assertEquals(data, inventory.get(slot).getData());
	}

	@Test
	public void testAddData() {
		int slot = getRandomSlot();
		int data = getRandomSize();
		int oldData = inventory.get(slot).getData();
		inventory.addData(slot, data);
		assertEquals(oldData + data, inventory.get(slot).getData());
	}

	@Test
	public void testSetAmount() {
		int slot = getRandomSlot();
		int amount = getRandomSize();
		inventory.setAmount(slot, amount);
		assertEquals(amount, inventory.get(slot).getAmount());
	}

	@Test
	public void testAddAmount() {
		int slot = getRandomSlot();
		int amount = getRandomSize();
		int oldAmount = inventory.get(slot).getAmount();
		inventory.addAmount(slot, amount);
		assertEquals(oldAmount + amount, inventory.get(slot).getAmount());
	}

	@Test
	public void testIsEmpty() {
		inventory.clear();
		assertTrue(inventory.isEmpty());
	}

	@Test
	public void testGet() {
		int slot = getRandomSlot();
		ItemStack item = getRandomItem();
		inventory.set(slot, item);
		assertEquals(item, inventory.get(slot));
	}

	@Test
	public void testIndexOf() {
		int slot = getRandomSlot();
		ItemStack item = getRandomItem();
		inventory.set(slot, item);
		assertEquals(slot, inventory.indexOf(item));
	}

	@Test
	public void testLastIndexOf() {
		int slot = random.nextInt(19);
		int lastSlot = slot + 1;
		ItemStack item = getRandomItem();
		inventory.set(slot, item);
		inventory.set(lastSlot, item);
		assertEquals(lastSlot, inventory.lastIndexOf(item));
	}

	@Test
	public void testSubList() {
		List<ItemStack> subList = inventory.subList(5, 15);
		for (int i = 0; i < subList.size(); i++) {
			assertEquals(inventory.get(i + 5), subList.get(i));
		}
	}

	private ItemStack getRandomItem() {
		return new ItemStack(mats[random.nextInt(3)], getRandomSize());
	}

	private Material getRandomMaterial() {
		return mats[random.nextInt(3)];
	}

	private int getRandomSize() {
		return random.nextInt(64) + 1;
	}

	private int getRandomSlot() {
		return random.nextInt(20);
	}
}
