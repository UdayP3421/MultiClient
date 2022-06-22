/*
 * package com.service;
 * 
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.junit.Assert.assertEquals; import static org.mockito.Mockito.when;
 * 
 * import java.util.ArrayList; import java.util.List; import java.util.Optional;
 * 
 * import
 * org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.
 * WhenClause; import org.junit.jupiter.api.Test; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.Mockito; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.entity.CartP; import com.entity.CustomerReg; import
 * com.entity.Plant; import com.repository.CartRepository; import
 * com.serviceimpl.CartServiceImpl;
 * 
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest public class CartServiceTest {
 * 
 * @InjectMocks private CartServiceImpl cartservice;
 * 
 * @Mock private CartRepository cartrepo;
 * 
 * @Test public void addCart() { CartP cart = new CartP(); cart.setCartId(1);
 * when(cartservice.addProduct(cart)).thenReturn(cart); assertEquals(cart,
 * cartservice.addProduct(cart)); }
 * 
 * @Test
 * 
 * void testGetCartById() throws Throwable { CartP c= new CartP();
 * c.setCartId(1);
 * 
 * Optional<CartP> c2=Optional.of(c);
 * 
 * Mockito.when(cartrepo.findById(1)).thenReturn(c2);
 * 
 * assertThat(cartservice.getCartById(1)).isEqualTo(c); }
 * 
 * 
 * @Test void testUpdatePlant() throws Throwable {
 * 
 * CustomerReg cs = new CustomerReg(); cs.setCustomerId(1);
 * cs.setCustomerName("uday"); cs.setCustomerPhone("9724638978");
 * cs.setCustomerEmail("Uday@Gamil.com"); cs.setPassword("Uday@123");
 * 
 * CartP c = new CartP(); c.setCartId(1); c.setCustomerreg(cs);
 * 
 * 
 * Optional<CartP> c2=Optional.of(c);
 * 
 * Mockito.when(cartrepo.findById(1)).thenReturn(c2);
 * 
 * Mockito.when(cartrepo.save(c)).thenReturn(c); cs.setCustomerName("udayp");
 * cs.setCustomerPhone("9724638678"); cs.setCustomerEmail("Uday12@Gamil.com");
 * cs.setPassword("Uday@1234"); c.setCustomerreg(cs);
 * 
 * assertThat(cartservice.updateCart(c)).isEqualTo(c); } }
 */
