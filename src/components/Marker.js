import React from 'react';
import {requireNativeComponent, Platform} from 'react-native';
import resolveAssetSource from 'react-native/Libraries/Image/resolveAssetSource';

const NativeMarker = requireNativeComponent('YamapMarker');

export default class Marker extends React.Component {
  state = {
    recreateKey: false,
    children: this.props.children,
  };

  static getDerivedStateFromProps(nextProps, prevState) {
    return {
      children: nextProps.children,
      recreateKey: Boolean(nextProps.children),
    };
  }

  resolveImageUri(img) {
    return img ? resolveAssetSource(img).uri : '';
  }

  render() {
    const props = {...this.props};
    return (
      <NativeMarker
        {...props}
        key={this.state.recreateKey}
        source={this.resolveImageUri(this.props.source)}
      />
    );
  }
}
