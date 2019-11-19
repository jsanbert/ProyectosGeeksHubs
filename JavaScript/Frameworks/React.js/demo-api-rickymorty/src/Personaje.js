/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable no-useless-constructor */
import React from 'react';

export default class Personaje extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <article>
            <img src={this.props.item.image}/>
                <p key>{this.props.item.name}</p>
                <p key>{this.props.item.gender}</p>
                <p key>{this.props.item.species}</p>
            </article>
        );
    }
}