import {Fragment, useEffect, useState} from 'react'
import './App.css'
import {imagenCliente} from "./service/ClienteService";

function App() {

    const [imagen, setImagen] = useState<any>(null);

    const img = async () => {
        await imagenCliente("748eb65e-3662-4784-b4fa-cf069d4a6c5f-file").then((response: any) => {
            let data: string = response.data;
            setImagen(data);
        });
    }

    useEffect(() => {
        img().then(() => null);
    }, []);

    return (
        <Fragment>
           <img src={imagen} alt="img" width={300} height={280}/>
        </Fragment>
    )
}

export default App
