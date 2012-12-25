package org.spout.api.component.impl;

import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;

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
	@Override
	public void onAttached() {
		if (!(getOwner() instanceof Player)) {
			throw new IllegalStateException("Cannot attach Player physics to a non player!");
		}
	}

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
