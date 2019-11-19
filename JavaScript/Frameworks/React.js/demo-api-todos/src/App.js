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
      "id": "",
      "user": "",
      "desc": "",
      "targetDate": "",
      "done": ""
    };
  };

  componentDidMount() {
    this.callApi();
  };

  callApi() {
    const data= {
      id: this.state.id,
      user: this.state.user,
      desc: this.state.desc,
      targetDate: this.state.targetDate,
      done: this.state.done
    };

    console.log(data);

    axios.post(`http://localhost:8080/api/v1/users/${data.user}/todos`, data)
      .then(result => console.log('result'))
      .catch(err => console.warn(err));
  };

  render() {
    return (
      <div>
        <label for="id">id:</label>
        <input id="id" type="text" name="id" value={this.state.id} onChange={event => { this.setState({id: event.target.value}); this.render()} } />
        <br/>
        <label for="user">user:</label>
        <input id="user" type="text" name="user" value={this.state.user} onChange={event => { this.setState({user: event.target.value}); this.render()} } />
        <br/>
        <label for="desc">desc:</label>
        <input id="desc" type="text" name="desc" value={this.state.desc} onChange={event => { this.setState({desc: event.target.value}); this.render()} } />
        <br/>
        <label for="targetDate">targetDate:</label>
        <input id="targetDate" type="text" name="targetDate" value={this.state.targetDate} onChange={event => { this.setState({targetDate: event.target.value}); this.render()} } />
        <br/>
        <label for="targetDate">done:</label>
        <input id="done" type="text" name="done" value={this.state.done} onChange={event => { this.setState({done: event.target.value}); this.render()} } />
        <br/>
        <button type="button" onClick={() => this.callApi() }>Enviar</button>

        <br/>

        <pre>
        data = {'{'}
          "id": "{ this.state.id }",
          "user": "{ this.state.user }",
          "desc": "{ this.state.desc }",
          "targetDate": "{ this.state.targetDate }",
          "done": "{ this.state.done }"
          {'}'}
        </pre>
      </div>
    );
  };

}
