const SERVER = 'http://localhost:8080'

const api = async (endpoint: string, config?: RequestInit) => {
    try{
        console.log(`Endpoint: (TL) ${SERVER}${endpoint}`)

        const result = await fetch(SERVER + endpoint, config)

        if (!result.ok){
            throw new Error(`Erro na requisição: ${result.status} ${result.statusText}`);

        }

        return result.json()

    } catch (error){
        console.error("Falha ao buscar dado da API:", error);
        throw error;
    }

    
    
}

export default api //import api from ...
//export { api } //import { api } from ...