import React, { useState, type FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';

interface EventInterface {
    id: string,
    type: string,
    description: string,
    date: Date,
    startSales: Date,
    endSales: Date,
    price: number
}

const CreateEvent = () => {
    const [type, setType] = useState('');
    const [description, setDescription] = useState('');
    const [date, setDate] = useState('');
    const [startSales, setStartSales] = useState('');
    const [endSales, setEndSales] = useState('');
    const [price, setPrice] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            const dados = { 
                type,
                description,
                date, 
                startSales, 
                endSales, 
                price 
            };
            console.log('Dados do evento a serem enviados:', dados);
            await api('/events', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dados),
            });
            alert('Evento criado com sucesso!');
            setType('');
            setDescription('');
            setDate('');
            setStartSales('');
            setEndSales('');
            setPrice('');
            navigate('/sales/events/events-list')
        } catch (error) {
            console.error('Erro ao criar evento:', error);
            alert('Erro ao criar evento. Verifique o console para mais detalhes.'); // Adicionado alerta de erro

        }
    };

    
    return (
        <div className="page-container">
            <div className="page-header">
                <div>
                    <h1>Criar Novo Evento</h1>
                    <p>Preencha as informações do evento abaixo</p>
                </div>
            </div>

            <form onSubmit={handleSubmit}>
                <div className="form-group full-width">
                    <label htmlFor="type">Tipo do Evento</label>
                    <select value={type} onChange={(event) => setType(event.target.value)}>
                        <option value="" disabled>Tipo do Evento</option>
                        <option value="PALESTRA">Palestra</option>
                        <option value="SHOW">Show</option>
                        <option value="TEATRO">Teatro</option>
                        <option value="CINEMA">Cinema</option>
                        <option value="FESTA">Festa</option>
                        <option value="FESTIVAL">Festival</option>
                        <option value="OUTROS">Outros</option>
                    </select>

                    <label htmlFor="description">Descrição do Evento</label>
                    <input
                        id="description"
                        type="text" 
                        value={description}
                        onChange={(event) => setDescription(event.target.value)}
                    />

                    <label htmlFor="date">Data do Evento</label>
                    <input
                        id="date"
                        type="datetime-local" 
                        value={date}
                        onChange={(event) => setDate(event.target.value)}
                    />

                    <label htmlFor="startSales">Data do Início de Vendas</label>
                    <input
                        id="startSales"
                        type="datetime-local" 
                        value={startSales}
                        onChange={(event) => setStartSales(event.target.value)}
                    />

                    <label htmlFor="endSales">Data do Fim das Vendas</label>
                    <input
                        id="endSales"
                        type="datetime-local" 
                        value={endSales}
                        onChange={(event) => setEndSales(event.target.value)}
                    />

                    <label htmlFor="price">Preço</label>
                    <input
                        id="price"
                        type="number" 
                        value={price}
                        onChange={(event) => setPrice(event.target.value)}
                    />
                </div>

                <div className="form-actions full-width">
                    <button type="button" className="btn btn-secondary" onClick={() => navigate('/sales/events/events-list')}>Cancelar</button>
                    <button type="submit" className="btn btn-primary">Criar Evento</button>
                </div>
            </form>
        </div>
        
    );
};

export default CreateEvent;
