package stepsDefnitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Teststep {

	@Given("I have a search field on amazon")
	public void bankPage() {
		System.out.println("Hello i am in bankpage");
	}
	
	@When("I search for a product named {string} and price {int}")
	public void i_search_for_a_product_named_and_price(String string, Integer int1) {
	}
	
	@Then("product with name {string} should be displayed")
	public void result(String name) {

	}
	
}
