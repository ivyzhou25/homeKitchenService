import axios from 'axios';

const API_BASE = 'http://localhost:8000/api';

export async function getBusinesses() {
  const res = await fetch("http://localhost:8000/getAllBiz"); 
  if (!res.ok) {
    throw new Error("Failed to fetch businesses");
  }
  return res.json();
}

export const getBusinessDetail = async (id: number) => {
  const res = await axios.get(`${API_BASE}/businesses/${id}`);
  return res.data;
};
