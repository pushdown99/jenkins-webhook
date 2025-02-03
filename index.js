const env = require('dotenv').config().parsed;

const express = require('express');
const app = express();
const PORT = env.PORT || 3000;

//////////////////
app.get('/', (req, res) => {
    res.send('Hello, World 2');
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});