/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutAPI is licensed under the SpoutDev License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
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
package org.spout.api.component.components;

import javax.vecmath.Vector3f;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;

import org.spout.api.data.Data;
import org.spout.api.math.MathHelper;
import org.spout.api.math.Vector3;

/**
 * A component that represents the physics object that is a motion of the entity within the world.
 */
public class PhysicsComponent extends EntityComponent {
	private CollisionObject collisionObject;
	private Vector3 lastLinearVelocity = Vector3.ZERO;
	private Vector3 lastAngularVelocity = Vector3.ZERO;

	public CollisionObject getCollisionObject() {
		return collisionObject;
	}

	//TODO Not thread safe at all...
	public void setCollisionObject(CollisionObject collisionObject) {
		if (collisionObject == null) {
			throw new IllegalStateException("Collision object is NOT allowed to be null!");
		}
		this.collisionObject = collisionObject;
	}

	public CollisionShape getCollisionShape() {
		return collisionObject.getCollisionShape();
	}

	public void setCollisionShape(CollisionShape shape) {
		collisionObject.setCollisionShape(shape);
	}

	public Vector3 getAngularVelocity() {
		Vector3f bVector = new Vector3f();
		collisionObject.getInterpolationAngularVelocity(bVector);
		return MathHelper.toVector3(bVector);
	}

	public Vector3 getLinearVelocity() {
		Vector3f bVector = new Vector3f();
		collisionObject.getInterpolationLinearVelocity(bVector);
		return MathHelper.toVector3(bVector);
	}

	public void setAngularVelocity(Vector3 velocity) {
		collisionObject.setInterpolationAngularVelocity(MathHelper.toVector3f(velocity));
	}

	public void setLinearVelocity(Vector3 velocity) {
		collisionObject.setInterpolationLinearVelocity(MathHelper.toVector3f(velocity));
	}

	public void setVelocity(Vector3 velocity) {
		setAngularVelocity(velocity);
		setLinearVelocity(velocity);
	}

	/**
	 * Returns whether the velocity has changed between the last and live velocity values.
	 * @return True if dirty, false if not
	 */
	public boolean isVelocityDirty() {
		return !lastAngularVelocity.equals(getAngularVelocity()) && !lastLinearVelocity.equals(getLinearVelocity());
	}

	public void copySnapshot() {
		lastAngularVelocity = getAngularVelocity();
		lastLinearVelocity = getLinearVelocity();
	}
}
