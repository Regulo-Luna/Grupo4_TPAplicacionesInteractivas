import React, { useState, useEffect } from 'react';
import { api } from '../api/apiClient';
import MetaCobranza from './MetaCobranza';


const Dashboard = () => {
  const [estadisticas, setEstadisticas] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchEstadisticas = async () => {
      try {
        setLoading(true);
        const response = await api.get('/dashboard/stats');
        setEstadisticas(response);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchEstadisticas();
  }, []);

  if (loading) return <div>Cargando métricas del sistema...</div>;
  if (error) return <div style={{ color: 'red' }}>Error: {error}</div>;

  return (
    <div style={styles.container}>
      <h2>Panel de Estadísticas</h2>
      <div style={styles.tarjetasMetricas}>
        <div style={styles.tarjeta}>
           <h3>Total Clientes</h3>
           <p style={styles.valor}>{estadisticas?.cantidadClientes || 0}</p>
        </div>
        <div style={styles.tarjeta}>
           <h3>Créditos Activos</h3>
           <p style={styles.valor}>{estadisticas?.cantidadCreditos || 0}</p>
        </div>
        <div style={styles.tarjeta}>
           <h3>Monto Total Financiado</h3>
           <p style={styles.valor}>{estadisticas?.montoTotalFinanciado || 0}</p>
        </div>
        <div style={styles.tarjeta}>
           <h3>Monto Total Cobrado</h3>
           <p style={styles.valor}>{estadisticas?.montoTotalCobrado || 0}</p>
        </div>
      </div>
        <div>
            <MetaCobranza />
        </div>
    </div>
    
    
  );
};

const styles = {
  container: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: '40px',
    backgroundColor: '#f4f7f6',
    minHeight: '100vh',
  },
  center: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
  },
  tarjetasMetricas: {
    display: 'flex',
    gap: '20px',
    justifyContent: 'center',
  },
  tarjeta: {
    backgroundColor: 'white',
    padding: '20px 40px',
    borderRadius: '12px',
    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
    textAlign: 'center',
    minWidth: '150px',
  },
  valor: {
    fontSize: '2rem',
    fontWeight: 'bold',
    color: '#007bff',
    margin: '10px 0 0 0',
  }
};

export default Dashboard;
