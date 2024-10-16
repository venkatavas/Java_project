const path = require('path');

module.exports = {
  entry: './src/App.js', // Your entry point
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js', // Your output file
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        enforce: 'pre',
        use: ['source-map-loader'],
        exclude: [
          /node_modules\/@react-aria\/ssr/
        ],
      },
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env', '@babel/preset-react'],
          },
        },
      },
      // Add other rules as needed
    ],
  },
  devtool: 'source-map', // Ensure source maps are enabled
  ignoreWarnings: [
    {
      module: /node_modules\/@react-aria\/ssr/,
      message: /Failed to parse source map/
    },
  ],
  resolve: {
    extensions: ['.js', '.jsx'],
  },
  devServer: {
    contentBase: path.join(__dirname, 'public'),
    compress: true,
    port: 9000,
  },
};
