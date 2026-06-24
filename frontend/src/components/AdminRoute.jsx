import React from 'react';
import { Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

export default function AdminRoute({ children }) {
  const { user } = useSelector((state) => state.auth);

  // Si no hay usuario logueado, lo mandamos al login
  if (!user) {
    return <Navigate to="/login" replace />;
  }

  // Si está logueado pero NO es administrador, lo mandamos al inicio/dashboard
  if (user.rol !== 'ADMIN') {
    return <Navigate to="/creditos" replace />; // o a "/clientes", la ruta que prefieras
  }

  // Si pasa ambas validaciones, mostramos el componente (GestorPermisos)
  return children;
}