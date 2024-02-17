"use strict"

const tableInfo = document.querySelector("#tabelaProdutos")

const findAll = () => {
    const resposta = fetch(
        'http://localhost:8080/products'
    ).then(x => x.json()).then(dados => {

        for(let i = 0; i < dados.length; i++){
            let linhaTabela = document.createElement("tr")
            linhaTabela.innerHTML = `<th>${dados[i].id}</th> <td>${dados[i].name}</td> <td>${dados[i].brand}</td> <td><type="button" id="${dados[i].id}" class="btn btn-outline-danger btn-sm" data-toggle="modal" data-target="#modalDeletar">Deletar</type=></td> <td><type="button" id="${dados[i].id}" class="btn btn-outline-warning btn-sm" data-toggle="modal" data-target="#modalEditar">Editar</type=></td>`
            tableInfo.appendChild(linhaTabela)

            const btnDeletar = linhaTabela.querySelector(".btn-outline-danger")
            btnDeletar.addEventListener("click", function(event) {

                const idDoBotao = event.target.id;
                console.log(idDoBotao)

                const deletarRegistro = document.querySelector("#deletarRegistro")
                deletarRegistro.addEventListener("click", (event) => {
                    fetch(`http://localhost:8080/delete/${idDoBotao}`, {
                        method: "DELETE"
                    })
                    window.location.reload()
                })
                
            });

            
            const btnEditar = linhaTabela.querySelector(".btn-outline-warning")
            btnEditar.addEventListener("click", (event)=>{
                const idDoBotao = event.target.id;
                console.log(idDoBotao)
               
                for(let cont = 0; cont < dados.length; cont++){
                    if(dados[i].id == idDoBotao){
                        document.querySelector("#nomeEditar").value = dados[i].name
                        document.querySelector("#marcaEditar").value = dados[i].brand
                    }
                }

                const editarRegistro = document.querySelector("#editarRegistro")
                editarRegistro.addEventListener("click", ()=>{
                    const nomeEditar = document.querySelector("#nomeEditar").value
                    const marcaEditar = document.querySelector("#marcaEditar").value


                    const produtoEditado = {
                        "name": nomeEditar,
                        "brand": marcaEditar
                    }

                    fetch(`http://localhost:8080/update/${idDoBotao}`, {
                        method: "PUT",
                        headers:{
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(produtoEditado)
                    })
                    window.location.reload()
                })
            })
        }
    })
}

findAll()

const insert = () => {
    const formulario = document.forms.namedItem("fomulario")
    const btn = document.querySelector("#btn")
    
    btn.addEventListener("click", (event) => {
        event.preventDefault()

        let nomeProduto = formulario.nome.value
        let marcaProduto = formulario.marca.value
    
        let produto = {
            "name": nomeProduto,
            "brand": marcaProduto
        }
    
        fetch("http://localhost:8080/register", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(produto) 
        }).then(res => res.json()).then(dado => {
            console.log(dado)
        })
        window.location.reload()
    })
}

insert()

