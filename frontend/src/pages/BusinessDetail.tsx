import React, { useEffect, useState } from 'react';
import { getBusinessDetail } from '../api';
import { useParams } from 'react-router-dom';
import { addToCart } from '../cart';

interface Dish {
  id: number;
  name: string;
  price: number;
}

interface Business {
  id: number;
  bizName: string;
  description: string;
}

interface BusinessDTO {
  business: Business;
  dishes: Dish[];
}

const BusinessDetail = () => {
  const { id } = useParams<{ id: string }>();
  const [business, setBusiness] = useState<Business | null>(null);
  const [dishes, setDishes] = useState<Dish[]>([]);
  const [loading, setLoading] = useState(true);

  // useEffect(() => {
  //   if (id) {
  //     // Fetch business info
  //     fetch(`http://localhost:8000/getBusinessById?id=${id}`)
  //       .then(res => res.json())
  //       .then(data => {
  //         business = data;
  //         renderContent();
  //       });

  //     // Fetch dishes for this business
  //     fetch(`http://localhost:8000/getDishByBiz?bizId=${id}`)
  //       .then(res => res.json())
  //       .then(data => {
  //         dishes = data;
  //         renderContent();
  //       });
  //   }
  // }, [id]);

  useEffect(() => {
    if (!id) return;

    const fetchBusiness = fetch(`http://localhost:8000/getBusinessById?id=${id}`).then(res => res.json());
    const fetchDishes = fetch(`http://localhost:8000/getDishByBiz?bizId=${id}`).then(res => res.json());

    Promise.all([fetchBusiness, fetchDishes])
      .then(([bizData, dishData]) => {
        setBusiness(bizData);
        setDishes(dishData);
        setLoading(false);
      })
      .catch(err => {
        console.error('Error fetching business details:', err);
        setLoading(false);
      });
  }, [id]);

  if (loading) return <p>Loading...</p>;
  if (!business) return <p>Business not found.</p>;

  // const renderContent = () => {
  //   const container = document.getElementById('business-detail-container');
  //   if (!container) return;

  //   container.innerHTML = ''; // clear old content

  //   if (!business) return; // wait until business is loaded

  //   const title = document.createElement('h1');
  //   title.textContent = business.bizName;
  //   container.appendChild(title);

  //   const desc = document.createElement('p');
  //   desc.textContent = business.description;
  //   container.appendChild(desc);

  //   const h2 = document.createElement('h2');
  //   h2.textContent = 'Dishes';
  //   container.appendChild(h2);

  //   const ul = document.createElement('ul');
  //   dishes.forEach(d => {
  //     const li = document.createElement('li');
  //     li.textContent = `${d.name} - $${d.price}`;
  //     ul.appendChild(li);
  //   });
  //   container.appendChild(ul);
  // };

  // return <div id="business-detail-container">Loading...</div>;

  return (
    <div style={{ padding: '20px' }}>
      <h1>{business.bizName}</h1>
      <p>{business.description}</p>

      <h2>Dishes</h2>
      <ul>
        {dishes.map(dish => (
          <li key={dish.id} style={{ marginBottom: '10px' }}>
            {dish.name} - ${dish.price.toFixed(2)}{' '}
            <button onClick={() => addToCart(dish)}>Add to Cart</button>
          </li>
        ))}
      </ul>
    </div>
  );

  // return (
  //   <div>
  //     <h1>{data.business.bizName}</h1>
  //     <p>{data.business.description}</p>

  //     <h2>Dishes</h2>
  //     <ul>
  //       {data.dishes.map(d => (
  //         <li key={d.id}>
  //           {d.name} - ${d.price.toFixed(2)}
  //         </li>
  //       ))}
  //     </ul>
  //   </div>
  // );
};

export default BusinessDetail;