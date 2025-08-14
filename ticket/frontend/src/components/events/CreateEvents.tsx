import React, { useState, type FormEvent } from 'react';
import axios from 'axios';

interface EventInterface {
    id: string,
    tipo: string,
    descricao: string,
    data: Date,
    data_inicio_vendas: Date,
    data_fim_vendas: Date,
    preco: number
}

const CreateEvent = () => {
    const [tipo, setTipo] = useState('');
    const [descricao, setDescricao] = useState('');
    const [data, setData] = useState('');
    const [data_inicio_vendas, setDataInicioVendas] = useState('');
    const [data_fim_vendas, setDataFimVendas] = useState('');
    const [preco, setPreco] = useState(0);
    //const [events, setEvents] = useState<EventInterface[]>([]);

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            const response = { tipo, descricao, data, data_inicio_vendas, data_fim_vendas, preco };
            console.log('Dados do evento a serem enviados:', response);
            await axios.post('/api/events', response);
            console.log('Evento criado com sucesso!');
            setTipo('');
            setDescricao('');
            setData('');
            setDataInicioVendas('');
            setDataFimVendas('');
            setPreco(0);
        } catch (error) {
            console.error('Erro ao criar evento:', error);
        }
    };

    
    return (
        <div className="App-header">
            <form onSubmit={handleSubmit}>
                <h2>Cadastrar Novo Evento</h2>
                <input type="text" value={tipo} onChange={(event) => setTipo(event.target.value)} placeholder="Tipo do Evento" />
                <input type="text" value={descricao} onChange={(event) => setData(event.target.value)} placeholder="Descrição do Evento"/>
                <input type="data" value={data} onChange={(event) => setData(event.target.value)} placeholder="Data do Evento" />
                <input type="data" value={data_inicio_vendas} onChange={(event) => setDataInicioVendas(event.target.value)} placeholder="Início das Vendas" />
                <input type="data" value={data_fim_vendas} onChange={(event) => setDataFimVendas(event.target.value)} placeholder="Fim das Vendas" />
                <input type="number" value={preco} onChange={(event) => setPreco(Number(event.target.value))} placeholder="Preço do Evento" />
                
                <button type="submit">Cadastrar Evento</button>
            </form>
        </div>
    );
};

export default CreateEvent;