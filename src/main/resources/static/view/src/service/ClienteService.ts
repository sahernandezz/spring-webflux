import http from "./conf";


export const imagenCliente = async (img: string) => {
    return await http.get(`/api/cliente/v1/img/${img}`, {
        headers: {}
    });
}
