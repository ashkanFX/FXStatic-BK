import express, { Request, Response } from 'express';
import helmet from 'helmet';
import cors from 'cors';
import morgan from 'morgan';
import rateLimit from 'express-rate-limit';
import config from './config';
import routes from './routes/index';
import errorHandler from './middleware/errorHandler';

const app = express();
const port = config.port;

app.use(helmet());
// HTTP request logging
app.use(morgan('combined'));
app.use(cors({ origin: config.corsOrigin }));

// Rate limiter: 100 requests per 15 minutes per IP
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 100,
  standardHeaders: true,
  legacyHeaders: false,
});
app.use(limiter);

app.use(express.json());

app.get('/health', (_req: Request, res: Response) => res.json({ status: 'ok' }));

app.use('/api', routes);

app.use(errorHandler);

app.listen(port, () => {
  console.log(`Server listening on port ${port}`);
});
