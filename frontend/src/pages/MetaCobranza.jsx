import React, { useState, useEffect } from 'react';
import { getMetas, createMeta, deleteMeta, updateMeta } from '../api/metaCobranza';

const MetaCobranza = () => {
    const [metas, setMetas] = useState([]);
    const [formData, setFormData] = useState({ mes: '', montoObjetivo: '' });
    const [loading, setLoading] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    const [editingId, setEditingId] = useState(null);
    useEffect(() => {
        fetchMetas();
    }, []);

    const fetchMetas = async () => {
    setLoading(true);
    try {
        const response = await getMetas();
        
        console.log("Datos directos del backend:", response); 
        
        setMetas(response || []);

    } catch (error) {
        console.error("Error al cargar metas:", error);
    } finally {
        setLoading(false);
    }
};
    const startEdit = (meta) => {
        setIsEditing(true);
        setEditingId(meta.id);
        setFormData({ mes: meta.mes, montoObjetivo: meta.montoObjetivo });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (isEditing) {
                await updateMeta(editingId, formData);
            } else {
                await createMeta(formData);
            }
            setFormData({ mes: '', montoObjetivo: '' });
            setIsEditing(false);
            setEditingId(null);
            fetchMetas();
        } catch (error) {
            alert("Error al guardar/actualizar: " + (error.message || "Error desconocido"));
        }
    };
    const handleCreate = async (e) => {
        e.preventDefault();
        try {
            await createMeta(formData);
            
            setFormData({ mes: '', montoObjetivo: '' });
            await fetchMetas();
        } catch (error) {
            console.error("Error detallado:", error);
        alert("Error al guardar: " + (error.response?.data?.message || "Revisa la consola"));
        }
    };

    const handleDelete = async (id) => {
        if(window.confirm("¿Seguro que deseas eliminar esta meta?")) {
            await deleteMeta(id);
            fetchMetas();
        }
    };

    return (
        <div style={styles.container}>
            <h2>Gestión de Metas Financieras</h2>
            
            <form onSubmit={handleSubmit} style={styles.form}>
                <input 
                    placeholder="Mes (ej: Enero)" 
                    value={formData.mes} 
                    onChange={e => setFormData({...formData, mes: e.target.value})} 
                    required 
                    style={styles.input}
                />
                <input 
                    type="number" 
                    placeholder="Monto Objetivo" 
                    value={formData.montoObjetivo} 
                    onChange={e => setFormData({...formData, montoObjetivo: e.target.value})} 
                    required 
                    style={styles.input}
                />
                <button type="submit" style={isEditing ? styles.buttonUpdate : styles.buttonAdd}>
                    {isEditing ? "Actualizar Meta" : "Agregar Meta"}
                </button>
                {isEditing && (
                    <button type="button" onClick={() => { setIsEditing(false); setFormData({ mes: '', montoObjetivo: '' }); }} style={styles.buttonCancel}>
                        Cancelar
                    </button>
                )}
            </form>

            <table style={styles.table}>
                <thead>
                    <tr><th>Mes</th><th>Monto</th><th>Acciones</th></tr>
                </thead>
                <tbody>
                    {metas?.map(m => (
                        <tr key={m.id}>
                            <td>{m.mes}</td>
                            <td>${Number(m.montoObjetivo).toLocaleString()}</td>
                            <td>
                                <button onClick={() => startEdit(m)} style={styles.buttonEdit}>Editar</button>
                                <button onClick={() => handleDelete(m.id)} style={styles.buttonDelete}>Eliminar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

const styles = {
    container: { padding: '20px', maxWidth: '600px', margin: '0 auto' },
    form: { display: 'flex', gap: '10px', marginBottom: '20px' },
    input: { padding: '8px', flex: 1 },
    buttonAdd: { padding: '8px 16px', backgroundColor: '#28a745', color: 'white', border: 'none', cursor: 'pointer' },
    buttonUpdate: { padding: '8px 16px', backgroundColor: '#007bff', color: 'white', border: 'none', cursor: 'pointer' },
    buttonCancel: { padding: '8px 16px', backgroundColor: '#6c757d', color: 'white', border: 'none', cursor: 'pointer' },
    buttonEdit: { marginRight: '5px', backgroundColor: '#ffc107', border: 'none', cursor: 'pointer', padding: '5px' },
    buttonDelete: { backgroundColor: '#dc3545', color: 'white', border: 'none', cursor: 'pointer', padding: '5px' },
    table: { width: '100%', borderCollapse: 'collapse', marginTop: '10px' }
};
export default MetaCobranza;