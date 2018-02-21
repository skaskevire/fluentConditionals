package com.epam.fluentConditionals.builder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.fluentConditionals.builder.EndingExpressionBuilder;
import com.epam.fluentConditionals.state.ConditionState;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Test class for {@link ExpressionBuilder}
 * 
 * @author Raman_Skaskevich
 *
 */
public class ExpressionBuilderTest {
	
	@Mock
	StateContext stateContext;
	
	EndingExpressionBuilder expressionBuilder;
	
	@Before
	public void doBefore()
	{
		MockitoAnnotations.initMocks(this);	
		expressionBuilder = new EndingExpressionBuilder(stateContext);
	}
	
	@Test
	public void testBuildAndReturn()
	{
		ConditionState state = new ConditionState();
		Mockito.when(stateContext.getState()).thenReturn(state).thenReturn(null);
		
		expressionBuilder.buildAndReturn();
		
		Mockito.verify(stateContext, Mockito.times(2)).getState();
		Mockito.verify(stateContext).execute();
		Mockito.verify(stateContext).getReturnValue();
	}
	
	@Test
	public void testBuild()
	{
		ConditionState state = new ConditionState();
		Mockito.when(stateContext.getState()).thenReturn(state).thenReturn(null);
		
		expressionBuilder.build();
		
		Mockito.verify(stateContext, Mockito.times(2)).getState();
		Mockito.verify(stateContext).execute();
	}
	
	@Test
	public void testBuildWithEmptyState()
	{
		expressionBuilder.build();
		
		Mockito.verify(stateContext).getState();
		Mockito.verify(stateContext, Mockito.times(0)).execute();
	}
}
