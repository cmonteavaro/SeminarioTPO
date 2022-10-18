import { Overlay, createStyles } from '@mantine/core';
import logorefugio from "../../images/logorefugio.png";


const useStyles = createStyles((theme) => ({
    wrapper: {
      position: 'relative',
      paddingTop: 180,
      paddingBottom: 130,
      backgroundImage:
      'url("https://st2.depositphotos.com/1606449/7516/i/950/depositphotos_75163555-stock-photo-cats-and-dogs-hanging-paws.jpg")',
      backgroundSize: 'cover',
      backgroundPosition: 'center',
  
      '@media (max-width: 520px)': {
        paddingTop: 80,
        paddingBottom: 50,
      },
    },
  
    inner: {
      position: 'relative',
      zIndex: 1,
    },
  
    highlight: {
      color: theme.colors[theme.primaryColor][4],
    
    },
  
    controls: {
      marginTop: theme.spacing.xl * 1.5,
      display: 'flex',
      justifyContent: 'center',
      paddingLeft: theme.spacing.md,
      paddingRight: theme.spacing.md,
  
      '@media (max-width: 520px)': {
        flexDirection: 'column',
      },
    },
  
    control: {
      height: 42,
      fontSize: theme.fontSizes.md,
  
      '&:not(:first-of-type)': {
        marginLeft: theme.spacing.md,
      },
  
      '@media (max-width: 520px)': {
        '&:not(:first-of-type)': {
          marginTop: theme.spacing.md,
          marginLeft: 0,
        },
      },
    },
  
    secondaryControl: {
      color: theme.white,
      backgroundColor: 'rgba(255, 255, 255, .4)',
  
      '&:hover': {
        backgroundColor: 'rgba(255, 255, 255, .45) !important',
      },
    },
  }));
  
  export function HeroImageBackground() {
    const { classes, cx } = useStyles();
  
    return (
      <div className={classes.wrapper}>
        <Overlay color="#000" opacity={0.65} zIndex={1} />
      </div>
    );
  }