// ShoppingCart.tsx
import React from 'react';
import { shoppingCart, getTotal, clearCart } from '../cart';

const ShoppingCart = () => {
  return (
    <div style={{ padding: '20px' }}>
      <h1>Shopping Cart</h1>

      {shoppingCart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          <ul>
            {shoppingCart.map((item) => (
              <li key={item.id}>
                {item.name} - ${item.price.toFixed(2)} (x{item.quantity})
              </li>
            ))}
          </ul>

          <p>
            <strong>Total: ${getTotal().toFixed(2)}</strong>
          </p>

          <button onClick={clearCart}>Clear Cart</button>
        </>
      )}
    </div>
  );
};

export default ShoppingCart;
