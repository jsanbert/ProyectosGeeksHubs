/* eslint-disable no-useless-constructor */
import React from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import Personaje from './Personaje.js';

export default class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      pnj: [],
      page: 1
    };
  }

  componentDidMount() {
    this.callApi();
  }

  callApi() {
    axios.get(`https://rickandmortyapi.com/api/character?page=${this.state.page}`)
      .then(data => {
        this.setState({pnj: data.data.results});
        console.log('STATE:', this.state.pnj);
      })
      .catch(error => {
        console.log(error)
      });
      this.render();
  }

  previo() {
    if (this.state.page > 1) {
      this.setState({page: this.state.page - 1});
      this.callApi();
    }
  }

  siguiente() {
    if (this.state.page >= 1) {
      this.setState({page: this.state.page + 1});
      this.callApi();
    }
  }

  render() {
    return (
      <div>
        <button onClick={() => this.previo()}>Previo</button>
        <button onClick={() => this.siguiente()}>Siguiente</button>
        <div className="grid">
        {
          this.state.pnj.map(item =>
            <Personaje item={item}/>
          )
        }
        </div>
      </div>
    );
  }

}
