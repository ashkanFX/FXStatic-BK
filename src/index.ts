import express, { Request, Response } from 'express';
import config from './config';
import routes from './routes';
import errorHandler from './middleware/errorHandler';

const app = express();
const port = config.port;

app.use(express.json());

app.get('/health', (_req: Request, res: Response) => res.json({ status: 'ok' }));

app.use('/api', routes);

app.use(errorHandler);

app.listen(port, () => {
  console.log(`Server listening on port ${port}`);
});
