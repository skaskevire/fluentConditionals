package com.epam.fluentConditionals.state;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.fluentConditionals.state.ConditionState;
import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;
import com.epam.fluentConditionals.state.StateEnum;

/**
 * Test class for {@link ConditionState}
 * 
 * @author Raman_Skaskevich
 *
 */
public class ConditionStateTest {
	
	@Mock
	public StateContext context;
	
	public ConditionState state;
	
	@Before
	public void doBefore()
	{
		state = new ConditionState();
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testChangeCurrentStateWhenConditionTrue()
	{
		state.setCondition(()->true);
		
		state.execute(context);
		
		Mockito.verify(context).getAvailableState(StateEnum.THEN);
		Mockito.verify(context).setState(Mockito.any(State.class));
	}
	
	@Test
	public void testChangeCurrentStateWhenConditionFalse()
	{
		state.setCondition(()->false);
		
		state.execute(context);
		
		Mockito.verify(context).getAvailableState(StateEnum.ELSE);
		Mockito.verify(context).setState(Mockito.any(State.class));
	}
}
