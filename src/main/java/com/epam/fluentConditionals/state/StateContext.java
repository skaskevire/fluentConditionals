package com.epam.fluentConditionals.state;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all available states and current state, return values and given params;
 * 
 * @author Raman_Skaskevich
 *
 */
public class StateContext{
	private State state;
	private Map<StateEnum, State> availableStates = new HashMap<StateEnum, State>();
	private Object returnValue;
	private Object givenParameter;

	@SuppressWarnings("unchecked")
	public <T> T getReturnValue() {
		return (T) returnValue;
	}
	public <T> void setReturnValue(T returnValue) {
		this.returnValue = returnValue;
	}
	public void setState(State state)
	{
		this.state = state;
	}
	public void addAvailableState(StateEnum state, State entity)
	{
		availableStates.put(state, entity);
	}
	public State getAvailableState(StateEnum state)
	{
		return availableStates.get(state);
	}
	public State getState()
	{
		return state;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T getGivenParameter() {
		return (T) givenParameter;
	}
	public<T> void setGivenParameter(T givenParameter) {
		this.givenParameter = givenParameter;
	}
	
	public void execute()
	{
		state.execute(this);
	}
}