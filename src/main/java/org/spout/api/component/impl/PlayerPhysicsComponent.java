/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * SpoutAPI is licensed under the Spout License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.api.component.impl;

import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.math.Vector3;

/**
 * The player physics component which acts as a controller, the driving force behind the entity.
 *
 * Players are a special case and are handled as the following:
 *
 * - Players have PairCacheGhostObjects which have their own callbacks for collisions.
 * - A KinematicCharacterController is the driving force that allows a client to move the entity in the world
 *   and have physics applied
 */
public abstract class PlayerPhysicsComponent extends EntityComponent {
	/**
	 * Moves the Player in 3D space.
	 * @param movement A Vector representing movement
	 */
	public abstract void move(Vector3 movement);

	/**
	 * Gets the restitution of the entity.
	 * <p>
	 * Note: Values are greater than or equal to zero
	 * </p>
	 * @return the restitution
	 */
	public abstract float getRestitution();

	/**
	 * Sets the restitution of the entity.
	 * <p>
	 * Restitution is the amount of restoring force applied when an object
	 * encounters a barrier.
	 * <br><br>
	 * Ex: A restitution value of 1 will result in an equal resulting
	 * force when colliding with an object. A value of 2 will result in
	 * a double force when colliding, whereas a value of 0.5F will result
	 * in half the force.
	 * </p>
	 * @param restitution new restitution of the object
	 */
	public abstract void setRestitution(float restitution);

	/**
	 * Sets the angular velocity.
	 * @param velocity the angular velocity
	 */
	public abstract void setAngularVelocity(Vector3 velocity);

	/**
	 * Sets the linear velocity.
	 * @param velocity the linear velocity
	 */
	public abstract void setLinearVelocity(Vector3 velocity);

	/**
	 * Teleports the player to the given location and inform's the player's client
	 *
	 * @param loc the new location
	 */
	public abstract void teleport(Point loc);

	/**
	 * Teleports the player to the given position and direction and inform's the player's client
	 *
	 * @param transform the new transform of the entity
	 */
	public abstract void teleport(Transform transform);
}
