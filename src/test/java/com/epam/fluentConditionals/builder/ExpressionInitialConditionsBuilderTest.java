package com.epam.fluentConditionals.builder;

import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.fluentConditionals.TestHelper;
import com.epam.fluentConditionals.builder.InitialConditionsBuilder;
import com.epam.fluentConditionals.builder.WhenExpressionBuilder;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Test class for {@link ExpressionInitialConditionsBuilder}
 * 
 * @author Raman_Skaskevich
 *
 */
public class ExpressionInitialConditionsBuilderTest {
	@Mock
	StateContext stateContext;

	@Before
	public void doBefore() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGivenConditionStateCreatedProperlyStringParam()
	{
		InitialConditionsBuilder eicb = new InitialConditionsBuilder(stateContext);
		String param = "param";
		WhenExpressionBuilder result = eicb.given(param);
		
		Assert.assertNotNull(result);
		Mockito.verify(stateContext).setGivenParameter(param);
	}

	@Test
	public void testGivenConditionStateCreatedProperlySupplierParam()
	{
		InitialConditionsBuilder eicb = new InitialConditionsBuilder(stateContext);
		Supplier<String> condition = TestHelper::getAString;
		WhenExpressionBuilder result = eicb.given(condition);
		
		Assert.assertNotNull(result);
		Mockito.verify(stateContext).setGivenParameter(condition.get());
	}
}
